import React from 'react';
import { BrowserRouter, Route, Routes as Switch } from 'react-router-dom';
import { Container, Row, Col } from 'react-bootstrap';

import 'bootstrap/dist/css/bootstrap.min.css';


import AppSideBar from './sidebar'
import AppHeader from './header'
import AppPageHome from '../pages/Home';
import AppPageUser from '../pages/User';
import AppPageSN from '../pages/SocialNetwork';

// https://getbootstrap.com/docs/5.0/examples/dashboard/
// https://github.com/react-bootstrap/code-sandbox-examples/blob/master/README.md

const AppDocument = function() {

  return (
    <React.Fragment>
      <BrowserRouter>
        <AppHeader />
        <Container fluid>
          <Row>
            <Col lg="2" bg="dark">
              <AppSideBar />
            </Col>
            <Col lg="10">
              <Switch>
                <Route exact path="/" element={<AppPageHome />} />
                <Route path="/user" element={<AppPageUser />} />
                <Route path="/social-network" element={<AppPageSN />} />
              </Switch>
            </Col>
          </Row>
        </Container>
      </BrowserRouter>
    </React.Fragment>
  ) ;
};


export default AppDocument;
