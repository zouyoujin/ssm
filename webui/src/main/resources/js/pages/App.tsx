import React, { PropTypes, Component } from 'react';
import { render } from 'react-dom';
//import { Provider } from 'react-redux';
//import { createStore, applyMiddleware } from 'redux';
import { Router, Route, Link, hashHistory, IndexRoute, Redirect, browserHistory, IndexLink, IndexRedirect } from 'react-router';

// pages
import Page from './../components/Page';
//import Index from './Index';
import UserAddPage from './user/UserAdd';
import LoginPage from './login/LoginPage';
import WelcomePage from './../components/welcome/WelcomePage';

const routes =
    <Route path="/" component={Page}>
        <IndexRoute component={WelcomePage} />
        <Route path='/login' component={LoginPage} />
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
