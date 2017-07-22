import React, { Component } from 'react';
import { Link } from 'react-router';
import { Layout, Menu, Breadcrumb, Icon } from 'antd';

const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;

import SiderCustom from './SiderCustom';
import HeaderCustom from './HeaderCustom';

import homeLayoutStyle from '../../../css/home-layout';

/**
 * 主页面布局
 */
class HomeLayout extends Component {

  state = {
    collapsed: false,
  };

  onCollapse = (collapsed) => {
    console.log(collapsed);
    this.setState({ collapsed });
  }

  render() {
    return (
      <Layout className="ant-layout-has-sider">
        <SiderCustom path={this.props.location.pathname} collapsed={this.state.collapsed} />
        <Layout>
          <HeaderCustom toggle={this.toggle} />
          <Content style={{ margin: '0 16px', overflow: 'initial' }}>
            {this.props.children}
          </Content>
          <Footer style={{ textAlign: 'center' }}>
            React-Admin ©2017 Created by 865470087@qq.com
          </Footer>
        </Layout>
      </Layout>
    );
  }
}

export default HomeLayout;