import React from 'react';
import MyTable from '../components/Table';


export default function(){

  return (

    <React.Fragment>
      <MyTable path="/user" columns={{"name":"Name", "email":"E-mail"}}></MyTable>
    </React.Fragment>
  );
};
