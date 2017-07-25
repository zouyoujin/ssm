import React from 'react';
import { Breadcrumb, Switch, Icon } from 'antd';
import { Link } from 'react-router';

class BreadcrumbCustom extends React.Component {

    render() {
        return (
            <Breadcrumb style={{ margin: '12px 0' }}>
                <Breadcrumb.Item><Link to={'/home'}>首页</Link></Breadcrumb.Item>
                {this.props.first ? <Breadcrumb.Item>{this.props.first}</Breadcrumb.Item> : ''}
                {this.props.second ? <Breadcrumb.Item>{this.props.second}</Breadcrumb.Item> : ''}
            </Breadcrumb>
        )
    }
}

export default BreadcrumbCustom;
