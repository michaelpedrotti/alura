import React from 'react';
import MyTable from '../components/Table';

export default function(){

  return (

    <React.Fragment>
      <MyTable path="/fruit" columns={{"name":"Name"}} paging="10"></MyTable>
    </React.Fragment>
  );
};
