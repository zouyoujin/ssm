import React, { PropTypes, Component } from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import { Router, Route, Link, hashHistory, IndexRoute, Redirect, browserHistory, IndexLink, IndexRedirect } from 'react-router';

// pages
import Page from './../components/Page';
import HomeLayout from './layouts/HomeLayout';
import LoginPage from './login/LoginPage';
import HomePage from './HomePage';
import UserAddPage from './user/UserAddPage';

//配置路由
render(
     <Router history={hashHistory}>
        <Route path={'/'} components={Page}>
            <IndexRedirect to="/home" />
            <Route path="/login" component={LoginPage} />
            <Route component={HomeLayout}>
                <Route path="/home" component={HomePage} />
                <Route path="/user/add" component={UserAddPage} />
            </Route>
        </Route>
    </Router>,
    document.getElementById('app')
);
