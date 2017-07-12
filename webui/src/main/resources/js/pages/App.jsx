import React, { PropTypes, Component } from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import { Router, Route, Link, hashHistory, IndexRoute, Redirect, browserHistory, IndexLink, IndexRedirect } from 'react-router';

// pages
import HomeLayout from './layouts/HomeLayout';
import LoginPage from './login/LoginPage';
import HomePage from './HomePage';
import UserAddPage from './user/UserAddPage';

// 配置路由
const routes =
    <Route component={HomeLayout}>
        <Route path="/" component={HomePage} />
        <Route path="/login" component={LoginPage} />
        <Route path="/user/add" component={UserAddPage} />
    </Route>;

render(
    <Router history={hashHistory}>
        {routes}
    </Router>,
    document.getElementById('app')
);
