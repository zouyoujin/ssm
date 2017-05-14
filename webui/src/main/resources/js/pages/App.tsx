import React, { PropTypes, Component } from 'react';
import ReactDom, { render } from 'react-dom';
//import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import { Router, Route, Link, hashHistory, IndexRoute, Redirect, browserHistory, IndexLink } from 'react-router';

// 引入Antd的导航组件
import { Menu, Icon } from 'antd';

// pages
import Home from './Home';
import Page1 from './demo/Page1';
import Page2 from './demo/Page2';
import Page3 from './demo/Page3';
import UserAddPage from './user/UserAdd';

// 配置路由
render((
    <Router history={hashHistory}>
        <Route path="/" component={Home}/>
        <Route path="/user/add" component={UserAddPage}/>
    </Router>
), document.getElementById('app'));