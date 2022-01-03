import React, { useState, useEffect } from 'react';
import {  Form, Button } from 'react-bootstrap';

const table = 'social_network';
const host = 'http://localhost:9090'

const load = function(setState, id){

  fetch(host + '/' + table +'/' + id)
    .then(function(result){

        return result.json();
    })
    .then(function(result){

        setState(result.data);
    });
};

const reset = function(setState, defaults){

  setState({...defaults});
}

const save = function(data){

  fetch(host + '/' + table, {
    method: 'POST',
    body: new URLSearchParams(data)

  })
  .then(function(result){

      return result.json();
  })
  .then(function(result){

      if(result.success){

        alert(result.msg || 'Saved');
      }
  });
};


export default function(){

  //console.log('enviroment', process.env.NODE_ENV);

  let defaults = {"name":""};

  let [data, setStateData] = useState({...defaults});
  let [stateLoad, setStateLoad] = useState(false);
  let [stateReset, setStateReset] = useState(false);

  useEffect(function(){ load(setStateData, 1); }, [stateLoad]);
  useEffect(function(){ reset(setStateData, defaults) }, [stateReset]);

  const onChangeField = function(e){

    let { name, value } = e.target;


    setStateData(function(state){ return {...state, [name]: value}; });
  };

  const onClickSave = function(e){

    save(data);
  };

  const onClickReset = function(){

    setStateReset(function(current){ return !current; });

  };

  const onClickReload = function(){

    setStateLoad(function(current){ return !current; });
  };


  return (
    <Form>

      <Form.Control type="hidden" name="id" value={data.id} />

      <Form.Group className="mb-3">
        <Form.Label>Name</Form.Label>
        <Form.Control type="text" name="name" value={data.name} onChange={onChangeField} />
      </Form.Group>
      <Button variant="secondary" onClick={onClickSave}>Save</Button>
      <Button variant="secondary" onClick={onClickReload}>Reload</Button>
      <Button variant="secondary" onClick={onClickReset}>Reset</Button>
      <label>{data.name}</label>
    </Form>
  );
};
