import React from 'react'
import { useTable, useRowSelect, usePagination } from 'react-table'
import {  Table, Pagination, Navbar, Container, Form } from 'react-bootstrap';
import Request from '../components/Request';


function AppTable({columns, data, fetchData, loading, pageCount: controlledPageCount}) {

  const hooks = function(hooks){

    hooks.visibleColumns.push(columns => [
        {
          id: 'selection',
          Header: function(self){

            return <Form.Check type="checkbox" {...self.getToggleAllRowsSelectedProps()} />;
          },
          Cell: function({ row }){

            console.log(row.getToggleRowSelectedProps());

            return <Form.Check type="checkbox" {...row.getToggleRowSelectedProps()} />;

          }
        },
        ...columns
      ]);
  };

  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    prepareRow,
    page,
    canPreviousPage,
    canNextPage,
    pageOptions,
    pageCount,
    gotoPage,
    nextPage,
    previousPage,
    setPageSize,
    selectedFlatRows,
    state: { pageIndex, pageSize },
  } = useTable({
      columns,
      data,
      initialState: { pageIndex: 0 },
      manualPagination: true,
      pageCount: controlledPageCount,
    },
    usePagination,
    useRowSelect,
    hooks
  );

  // Listen for changes in pagination and use the state to fetch our new data
  React.useEffect(() => {

    fetchData({ pageIndex, pageSize })
  },

  [fetchData, pageIndex, pageSize])

  console.log('page', page);

  // Render the UI for your table
  return (
    <React.Fragment>
      <Table striped bordered hover {...getTableProps()}>
        <thead>
          {headerGroups.map(headerGroup => (
            <tr {...headerGroup.getHeaderGroupProps()}>
              {headerGroup.headers.map(function(column, index){
                return (<th {...column.getHeaderProps()} style={{'width':(index == 0 ? '1em' : 'auto' ) }}>
                    {column.render('Header')}
                    <span>
                      {column.isSorted ? column.isSortedDesc ? ' 🔽' : ' 🔼' : ''}
                    </span>
                  </th>
                );
              })}
            </tr>
          ))}
        </thead>
        <tbody {...getTableBodyProps()}>
          {page.map((row, i) => {
            prepareRow(row)
            return (
              <tr {...row.getRowProps()}>
                {row.cells.map(cell => {
                  return <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                })}
              </tr>
            )
          })}
          <tr>
            {loading ? (
              // Use our custom loading state to show a loading indicator
              <td colSpan="10000">Loading...</td>
            ) : (
              <td colSpan="10000">
                Showing {page.length} of ~{controlledPageCount * pageSize}{' '}
                results
              </td>
            )}
          </tr>
        </tbody>
      </Table>

      <Navbar>

        <Navbar.Text>
          {loading ? (
            // Use our custom loading state to show a loading indicator
            <td colSpan="10000">Loading...</td>
          ) : (
            <td colSpan="10000">

                  Showing { ((pageIndex + 1) * pageSize) - (pageSize - 1) } to { (pageIndex + 1) * pageSize } of {controlledPageCount * pageSize} entries
            </td>
          )}

        </Navbar.Text>


          <Navbar.Brand>
            <Pagination>
              <Pagination.First onClick={() => gotoPage(0)} disabled={!canPreviousPage} />
              <Pagination.Prev onClick={() => previousPage()} disabled={!canPreviousPage} />
              <Pagination.Next onClick={() => nextPage()} disabled={!canNextPage} />
              <Pagination.Last onClick={() => gotoPage(pageCount - 1)} disabled={!canNextPage} />
            </Pagination>
          </Navbar.Brand>

          <Navbar.Text>Go to page:</Navbar.Text>

          <Navbar.Brand>
            <Form.Control type="number" defaultValue={pageIndex + 1} onChange={e => {
                const page = e.target.value ? Number(e.target.value) - 1 : 0
                gotoPage(page)
              }}
              style={{ width: '5rem' }}
            />
          </Navbar.Brand>

          <Navbar.Brand>
            <Form.Select value={pageSize} onChange={e => { setPageSize(Number(e.target.value)) }}>
              {[10, 20, 30, 40, 50].map(pageSize => (
                <option key={pageSize} value={pageSize}>
                  Show {pageSize}
                </option>
              ))}
            </Form.Select>
          </Navbar.Brand>

      </Navbar>

      <code>
           {JSON.stringify(
             {
               'selected': selectedFlatRows.map(
                d => d.original
              ),
             },
             null,
             2
           )}
         </code>

    </React.Fragment>
  )
}

// Let's simulate a large dataset on the server (outside of our component)
const serverData = []

function App() {
  const columns = React.useMemo(
    () => [
      {
        Header: 'Tile',
        accessor: 'title'
      },
      {
        Header: 'Year',
        accessor: 'year',
      },
      {
        Header: 'Runtime',
        accessor: 'runtime',
      },
    ],
    []
  )

  const [data, setData] = React.useState([]);
  const [total, setTotal] = React.useState(0);
  const [loading, setLoading] = React.useState(false);
  const [pageCount, setPageCount] = React.useState(0);

  const fetchData = React.useCallback(({ pageSize, pageIndex }) => {

    setLoading(true);

    Request.get('/movies?start=' + (pageIndex * pageSize) + '&limit=' + pageSize, function(result){

        setData(result.rows);
        setTotal(result.total);

        setLoading(false);

        setPageCount(Math.ceil(result.total / pageSize));
    });



  }, []);

  return (
      <AppTable
        columns={columns}
        data={data}
        fetchData={fetchData}
        loading={loading}
        pageCount={pageCount}
      />
  )
}

export default App
