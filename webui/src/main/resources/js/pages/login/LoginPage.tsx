import React, { PropTypes } from 'react';
import { Input, Button, Row, Col, Form, message } from 'antd';
import { bindActionCreators } from 'redux';

// 加载其它组件
import action from '../../utils/action';
import './loginPage.less';
//定义常量列表
const FormItem = Form.Item;

//Message Setting
message.config({ top: 100 });

// 定义界面属性接口，验证属性参数类型
interface LoginProps extends React.Props<any> {
    [key: string]: any;
    form: any;
    api: any;
    router: any;
    loginErrors: string;
};

// 定义界面状态接口，验证状态参数类型
interface State {
};

// 构建界面
class LoginPage extends React.Component<LoginProps, State> {

    static propTypes = {
        match: PropTypes.object.isRequired,
        location: PropTypes.object.isRequired,
        history: PropTypes.object.isRequired
    };

    constructor(props, context) {
        super(props, context);
    };

    componentWillReceiveProps(nextProps) {
        const error = nextProps.loginErrors;
        const isLoggingIn = nextProps.loggingIn;
        const user = nextProps.user;
        if (error != this.props.loginErrors && error) {
            message.error('Please check your account or password');
        }
        if (!isLoggingIn && !error && user) {
            message.success('Login successfully');
        }
        if (user) {
            this.props.history.push("/home");
        }
    };

    handleSubmit(e) {
        e.preventDefault();
        const data = this.props.form.getFieldsValue();
        let dataObj = {
            type: "USER_LOGIN",
            url: "/user/login",
            data: data
        }
        this.props.api.postData(dataObj);
    };

    render() {
        const { getFieldDecorator } = this.props.form;
        return (
            <Row className="login-row" type="flex" justify="space-around" align="middle">
                <Col span={8}>
                    <div className="login-form">
                        <Form layout="horizontal" onSubmit={this.handleSubmit.bind(this)}>
                            <FormItem label="User:" labelCol={{ span: 6 }} wrapperCol={{ span: 14 }}>
                                {getFieldDecorator("user", { initialValue: '' })(
                                    <Input id="account" placeholder='Please input your account' />
                                )}
                            </FormItem>
                            <FormItem label="Password:" labelCol={{ span: 6 }} wrapperCol={{ span: 14 }}>
                                {getFieldDecorator("password", { initialValue: '' })(
                                    <Input type='password' id="password" placeholder='Please input your password' />
                                )}
                            </FormItem>
                            <Row><Col span={24} offset={10}>
                                <Button type='primary' htmlType='submit'>Login</Button>
                            </Col></Row>
                        </Form>
                    </div>
                </Col>
            </Row>
        )
    };
}

const LoginPageForm = Form.create()(LoginPage);

function mapStateToProps(state: any): any {
    const { user } = state;
    if (user.user) {
        return { user: user.user, loggingIn: user.loggingIn, loginErrors: '' };
    }
    return { user: null, loggingIn: user.loggingIn, loginErrors: user.loginErrors };
}

const mapDispatchToProps = (dispatch: any) => {
    return {
        api: bindActionCreators(action, dispatch)
    }
}

export default LoginPageForm;

