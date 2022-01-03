import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Breadcrumb, ButtonGroup, Button, Table, Form, Modal } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlusCircle, faEdit, faMinusCircle, faCheckCircle, faMinus } from '@fortawesome/free-solid-svg-icons'

export default function(){

  const [show, setShow] = useState(false);
  const handleClose = function(){ setShow(false) };
  const handleShow = function(){ setShow(true); }


   const [refresh, setRefresh] = useState(0);


  const [rows, setRows] = useState([]);
  let [data, setData] = useState({});

  const onChangeData = function(e){

    let field = e.target;

    if((field.type === 'checkbox' || field.type === 'radio') && field.checked){

      data[field.name] = field.value;
    }
    else {

      data[field.name] = field.value;
    }

    setData(data);
  };

  useEffect(function(){

    fetch('http://localhost:9090/user')
      .then(function(result){

          return result.json();
      })
      .then(function(result){

          setRows(result.rows);
      });

  }, [refresh]);

  const remove = function (){

    let data = {};

    Array.from(document.querySelectorAll("input[name^='id['")).forEach(function(field){

      if(field.checked){

        data[field.name] = field.value;
      }
    });

    // https://jasonwatmore.com/post/2021/09/19/react-hook-form-set-form-values-in-useeffect-hook-after-async-data-load
    fetch('http://localhost:9090/user', {
      method: 'DELETE',
      body: new URLSearchParams(data)

    })
    .then(function(result){

        return result.json();
    })
    .then(function(result){

        if(result.success){

          setRefresh(key => key + 1);

          alert(result.msg);
        }
    });

  };

  const submit = async function(){


    fetch('http://localhost:9090/user', {
      method: 'POST',
      body: new URLSearchParams(data)

    })
    .then(function(result){

        return result.json();
    })
    .then(function(result){

        if(result.success){

          handleClose();
          setRefresh(key => key + 1);
        }
    });

  };

  const load = function(){

    let arr = Array.from(document.querySelectorAll("input[name^='id['"));

    arr = arr.filter(function(field){ return field.checked; });

    if(arr.length === 0){

      alert('Select one row');
      return;
    }

    let field = arr.shift();

    handleShow();


    fetch('http://localhost:9090/user/' + field.value, { method: 'GET' })
      .then(function(result){

          return result.json();
      })
      .then(function(result){

        if(!result.success){
          alert(result.msg);
          return;
        }

        console.log(result);

        setData(result.data);
      });
  };

  const create = function(){

    // https://reactjs.org/docs/forms.html
    handleShow();
    setData({});
  };


  return (
    <React.Fragment>

      <Breadcrumb>
        <Breadcrumb.Item>
          <Link to="/">Home</Link>
        </Breadcrumb.Item>
        <Breadcrumb.Item active>User</Breadcrumb.Item>
      </Breadcrumb>

      <ButtonGroup aria-label="Basic example" style={{"marginBottom":"1rem"}}>
        <Button variant="secondary" onClick={create}>
          <FontAwesomeIcon icon={faPlusCircle} /> Add
        </Button>
        <Button variant="secondary" onClick={load}>
          <FontAwesomeIcon icon={faEdit} /> Edit
        </Button>
        <Button variant="secondary" onClick={remove}>
          <FontAwesomeIcon icon={faMinusCircle} /> Remove
        </Button>
      </ButtonGroup>

      <Table striped bordered hover>
        <thead>
          <tr>
            <th style={{"width":"3rem"}}>
              <Form.Check type="checkbox" id="default-checkbox" />
            </th>
            <th>Name</th>
            <th>E-mail</th>
            <th style={{"width":"5rem"}}>Active</th>
            <th style={{"width":"5rem"}}>Removed</th>
          </tr>
        </thead>
        <tbody>
          {rows.map((row, idx) => (

            <tr key={"user-" + row.id}>
              <td>
                <Form.Check type="checkbox" name={"id[" + idx + ']'} value={row.id}  />
              </td>
              <td>{row['name']}</td>
              <td>{row['email']}</td>
              <td align="center"><FontAwesomeIcon icon={row['active'] === 'Y' ? faCheckCircle : faMinus } /></td>
              <td align="center"><FontAwesomeIcon icon={row['removed'] === 'Y' ? faCheckCircle : faMinus } /></td>
            </tr>
          ))}
        </tbody>
      </Table>

      <Modal show={show} onHide={handleClose}>
         <Modal.Header closeButton>
           <Modal.Title>User</Modal.Title>
         </Modal.Header>
         <Modal.Body>
          <Form>

            <Form.Control type="hidden" name="id" defaultValue={data.id} />

            <Form.Group className="mb-3">
              <Form.Label>Name</Form.Label>
              <Form.Control type="text" name="name" defaultValue={data.name} onChange={e => onChangeData(e)} />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>E-mail</Form.Label>
              <Form.Control type="email" name="email" defaultValue={data.email} onChange={e => onChangeData(e)} />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Check type="checkbox" name="active" defaultValue={data.active} value="Y" label="Active" />
            </Form.Group>

            </Form>
         </Modal.Body>
         <Modal.Footer>
           <Button variant="secondary" onClick={handleClose}>
             Close
           </Button>
           <Button variant="primary" onClick={submit}>
             Save
           </Button>
         </Modal.Footer>
       </Modal>

    </React.Fragment>
  );
};
