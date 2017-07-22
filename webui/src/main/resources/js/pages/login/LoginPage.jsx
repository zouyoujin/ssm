import React from 'react';
import { Form, Icon, Input, Button, Checkbox, message } from 'antd';

import './loginPage.less';
//定义常量列表
const FormItem = Form.Item;

//Message Setting
message.config({ top: 100, duration: 2 });

// 构建界面
class LoginPage extends React.Component {

    constructor(props) {
        super(props);
    }

    handleSubmit(e) {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
                message.success("loading......");
            } else {
                message.error("请输入正确的用户名和密码!");
            }
        });
    };

    render() {
        const { getFieldDecorator } = this.props.form;
        return (
            <div className="login">
                <div className="login-form" >
                    <div className="login-logo">
                        <span>React Admin</span>
                    </div>
                    <Form onSubmit={this.handleSubmit.bind(this)} style={{ maxWidth: '300px' }}>
                        <FormItem>
                            {getFieldDecorator("userName", {
                                rules: [{ required: true, message: '请输入用户名!' }],
                            })(
                                <Input id="userName" prefix={<Icon type="user" style={{ fontSize: 13 }} />} placeholder="用户名" />
                                )}
                        </FormItem>
                        <FormItem>
                            {getFieldDecorator('password', {
                                rules: [{ required: true, message: '请输入密码!' }],
                            })(
                                <Input prefix={<Icon type="lock" style={{ fontSize: 13 }} />} type="password" placeholder="密码" />
                                )}
                        </FormItem>
                        <FormItem>
                            {getFieldDecorator('remember', {
                                valuePropName: 'checked',
                                initialValue: true,
                            })(
                                <Checkbox>记住我</Checkbox>
                                )}
                            <a className="login-form-forgot" href="" style={{ float: 'right' }}>忘记密码</a>
                            <Button type="primary" htmlType="submit" className="login-form-button" style={{ width: '100%' }}>
                                登录
                        </Button>
                            或 <a href="">现在就去注册!</a>
                            <p>
                                <Icon type="github" onClick={this.gitHub} />(第三方登录)
                            </p>
                        </FormItem>
                    </Form>
                </div>
            </div>
        )
    };
}

export default Form.create()(LoginPage);

