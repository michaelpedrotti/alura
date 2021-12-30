import React from 'react';

//import { BrowserRouter, Route, Routes as Switch } from 'react-router-dom';

// react-router-dom@6 rename Switch to Routes :(
// https://react-bootstrap.github.io/components/alerts

//import Home from './pages/Home'

import AppDocument from './layout/document';

function App() {

  return ( <AppDocument /> );

  // return (
  //
  //   <BrowserRouter>
  //     <Switch>
  //       <Route exact path="/" element={<Home />} />
  //     </Switch>
  //   </BrowserRouter>
  // );
}



export default App;
