import React from 'react';
import { Link } from 'react-router-dom';
import { Nav } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faUsers, faCoffee } from '@fortawesome/free-solid-svg-icons'

export default function(){

  console.log(require('@fortawesome/free-solid-svg-icons'));

  // height: calc(100vh - 48px);
  return (
    <Nav className="col-md-12 d-none d-md-block bg-light sidebar" activeKey="/home">
      <div className="sidebar-sticky">
        <Nav.Item>
          <Nav.Link href="/home">
            <Link to="/user" className="">
              <FontAwesomeIcon icon={faUsers} /> Users
            </Link>
          </Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link eventKey="link-1">
            <Link to="/social-network" className="">
              <FontAwesomeIcon icon={faCoffee} /> Social Networks
            </Link>
          </Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link eventKey="link-2">Link</Nav.Link>
        </Nav.Item>
        <Nav.Item>
          <Nav.Link eventKey="disabled" disabled>
          Disabled
          </Nav.Link>
        </Nav.Item>
      </div>
    </Nav>
  );
}
