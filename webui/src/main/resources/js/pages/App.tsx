import React, { PropTypes, Component } from 'react';
import ReactDom, { render } from 'react-dom';
//import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import { Router, Route, Link, hashHistory, IndexRoute, Redirect, browserHistory, IndexLink } from 'react-router';

// 引入Antd的导航组件
import { Menu, Icon } from 'antd';

// pages
import Index from './demo/Index';
import Page1 from './demo/Page1';
import Page2 from './demo/Page2';
import Page3 from './demo/Page3';

export default class Application extends Component<any, any> {
  render() {
    return (
      <div>
        <div className="header">
          <Link to="page1" activeClassName="active">page1</Link>
          <Link to="page2">page2</Link>
          <Link to="page3">page3</Link>
        </div>
        {this.props.children}
        <Index/>
      </div>
    );
  }
}

// 配置路由
render((
    <Router history={hashHistory}>
      <Route path="/" component={Application}>
        <IndexRoute component={Page1} />
        <Route path="page1" component={Page1}></Route>
        <Route path="page2" component={Page2}></Route>
        <Route path="page3" component={Page3}></Route>
      </Route>
    </Router>
), document.getElementById('app'));