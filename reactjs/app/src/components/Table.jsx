import React from 'react';
import { Table, Form, ButtonGroup, Button, Pagination, Row, Col } from 'react-bootstrap';
import Request from '../components/Request';


export default class MyTable extends React.Component {

  /**
   * init props
   */
  constructor(props) {

    super(props);

    this.state = {
      total: 0,
      rows: []
    };

    this.paging = props.paging || 30;
    this.start = 0;
    this.limit = this.paging;
    this.currentPage = 1;
    this.totalPages = 1;

    this.hasNextPage = this.hasNextPage = this.hasLastPage = true;

    this.path = props.path;
  }

  /**
   * start up component
   */
  componentDidMount() {

    this.fetch();
  }

  /**
   * mark all rows checkbox
   *
   * @return void
   */
  onSelectAll(e){

    let field = e.target;
    let elements = document.querySelectorAll("input[name^='id['");

    Array.from(elements).forEach(function(checkbox){

      checkbox.checked = field.checked;

    });
  }

  /**
   *
   *
   * @return void
   */
  refresh(){

    this.fetch();
  }



  /**
   * fetch data into table
   *
   * @param int id
   * @return void
   */
  fetch(){

    const self = this;
    const url = this.path + '?start=' + this.start + '&limit=' + this.limit;

    Request.get(url, function(result){

      if(result.success){

        self.setState({"total": result.total, "rows": result.rows});
      }
    });
  }

  pages(){

    this.totalPages = 1;
    this.hasNextPage = this.hasPrevPage = this.hasLastPage = false;

    if(this.state.total > this.paging){

      this.totalPages = parseInt(this.state.total / this.paging);
    }

    if(this.totalPages > 1){

      this.hasNextPage = this.hasPrevPage = this.hasPrevPage = true;
    }


    return Array.from({length: this.totalPages}, (val, key) => key + 1);
  }

  toPage(index){

    console.log('index', index);

    if(index === 1){

      this.start = 0;
    }
    else {

      this.start = index * this.paging;
    }

    this.fetch();
  }

  render() {

    const self = this;
    let rows = this.state.rows;
    let pages = this.pages();

    return (

      <React.Fragment>

        <ButtonGroup aria-label="Basic example" style={{"marginBottom":"1rem", "marginTop":"1rem"}}>
          <Button variant="secondary" onClick="">
            Add
          </Button>
          <Button variant="secondary" onClick="">
            Edit
          </Button>
          <Button variant="secondary" onClick="">
          Remove
          </Button>
        </ButtonGroup>

        <Table striped bordered hover>
          <thead>
            <tr>
              <th style={{"width":"3rem"}}>
                <Form.Check type="checkbox" onChange={self.onSelectAll} />
              </th>

              {Object.values(self.props.columns).map(function(label){

                return ( <th>{label}</th> );

              })}

            </tr>
          </thead>
          <tbody>
            {rows.map(function(row, idx){

              return (

                <tr key={"user-" + row.id}>
                  <td>
                    <Form.Check type="checkbox" name={"id[" + idx + ']'} value={row.id}  />
                  </td>

                  {Object.keys(self.props.columns).map(function(key){

                    return ( <td>{row[key]}</td> );

                  })}
                </tr>
              );
          })}
          </tbody>
        </Table>

        <Row>
          <Col lg="4">
            Showing {self.start + 1} to {self.state.total > self.limit ? self.limit : self.state.total} of {self.state.total} entries
          </Col>
          <Col lg="6">
            <Pagination>
              <Pagination.First onClick={function(){ self.toPage(1); }} />
              <Pagination.Prev disabled={!self.hasPrevPage} onClick={function(){ self.toPage(self.currentPage + 1); }} />
              {pages.map(function(page){

                return (
                    <Pagination.Item
                      onClick={function(){ self.toPage(page); }}
                      active={page === self.currentPage}>{page}
                    </Pagination.Item>
                  );
              })}
              <Pagination.Next disabled={!self.hasNextPage} onClick={function(){ self.toPage(self.currentPage - 1); }} />
              <Pagination.Last disabled={!self.hasLastPage} onClick={function(){ self.toPage(self.totalPages); }} />
            </Pagination>
            </Col>
        </Row>

      </React.Fragment>
     );
  }
}
