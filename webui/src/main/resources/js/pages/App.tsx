import React, { PropTypes, Component } from 'react';
import { render } from 'react-dom';
//import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import { Router, Route, Link, hashHistory, IndexRoute, Redirect, browserHistory, IndexLink, IndexRedirect } from 'react-router';

// pages
import Page from './../components/Page';
//import Index from './Index';
import UserAddPage from './user/UserAdd';


const routes =
    <Route path="/" component={Page}>
        <IndexRedirect to="/index/dashboard/index" />
        <Route path={'index'} component={UserAddPage}>
            <Route path="/user/add" component={UserAddPage} />
        </Route>
    </Route>;

// 配置路由
render(
  <Router history={hashHistory}>
      {routes}
  </Router>,
  document.getElementById('app')
);
