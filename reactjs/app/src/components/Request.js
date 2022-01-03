
const HOST = 'http://localhost:9090';

export default class Request {

  static get(path, fn){

    Request.execute(path, fn, {
      method: 'GET'
    });
  }

  /**
   *
   * @example https://developer.mozilla.org/en-US/docs/Web/API/URLSearchParams
   */
  static post(path, body, fn){

    Request.execute(path, fn, {
      method: 'POST',
      body: new URLSearchParams(body)
    });
  }


  static push(path, body, fn){}

  static delete(path, fn){}

  /**
   *
   * @example https://developer.mozilla.org/en-US/docs/Web/API/fetch
   * @example https://developer.mozilla.org/en-US/docs/Web/API/Response
   */
  static execute(path, fn, init){

    console.log('url', HOST + path);

    fetch(HOST + path, init)
      .then(function(response){

        return response.json();
      })
      .then(fn);
  }
};
