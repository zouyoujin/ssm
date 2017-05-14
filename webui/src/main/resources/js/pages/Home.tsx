import React from 'react';
import { Link } from 'react-router';

export default class Home extends React.Component<any, any>{
  render() {
    return (
      <div>
        <header>
          <h1>Welcome</h1>
        </header>
         <div className="header">
          <Link to="/demo/page1" activeClassName="active">page1</Link>
          <Link to="/demo/page2">page2</Link>
          <Link to="/demo/page3">page3</Link>
          <Link to="/user/add">添加用户</Link>
        </div>
      </div>
    );
  }
}