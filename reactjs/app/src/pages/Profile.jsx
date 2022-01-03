import React from 'react';
import swal from 'sweetalert';
import {  Form, Button } from 'react-bootstrap';
import Request from '../components/Request';

export default class Profile extends React.Component {

  /**
   * init props
   */
  constructor(props) {

    super(props);

    this.path = '/social_network';
    this.defaults = { "name":""}
    this.state = {...this.defaults};
  }

  /**
   * start up component
   */
  componentDidMount() {

    this.load(1);
  }

  /**
   * reset data from component
   *
   * @return void
   */
  reset(){

    // setState will not remove attributes, just overwrite them
    this.setState(
      function(state){ return {...this.defaults}; },
      function(){ delete this.state.id; }
    );
  }


  /**
   * load data into component
   *
   * @param int id
   * @return void
   */
  load(id){

    const self = this;

    Request.get(this.path + '/' + id, function(result){

      if(result.success){

        self.setState(result.data);
      }
    });
  }

  /**
   * submit data from this component to remote api
   */
  save(){

    const self = this;

    Request.post(this.path, self.state, function(result){

      if(result.success){

        swal("Success", result.msg || 'Saved');
      }
      else {

        swal("Fail", result.msg || 'Falhou');
      }
    });
  }

  /**
   * Event on change data state
   */
  onChange(e){

    let { name, value } = e.target;


    this.setState(function(state){

      return {...state, [name]: value};

    });
  }


  /**
   * Render component
   */
  render() {

    const self = this;

    return (

      <Form>
        <Form.Control type="hidden" name="id" value={self.state.id} />
        <Form.Group className="mb-3">
          <Form.Label>Name</Form.Label>
          <Form.Control type="text" name="name" value={self.state.name} onChange={self.onChange.bind(this)} />
        </Form.Group>
        <Button variant="secondary" onClick={() => self.save() }>Save</Button>
        <Button variant="secondary" onClick={() => self.load(2) }>Reload</Button>
        <Button variant="secondary" onClick={() => self.reset() }>Reset</Button>
        <label>{self.state.name}</label>
      </Form>
    );
  }
};
