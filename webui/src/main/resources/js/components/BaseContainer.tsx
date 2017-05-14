import * as React from "react";
import * as ReactDOM from "react-dom";
import { DatePicker } from 'antd';

export default class BaseComponent extends React.Component<any, any> {
  constructor(props) {
    super(props);

    /**
     * 处理登录权限
     */
    let _data = false;
    /**
     * 如果本地存在就取本地数据，否则跳转到登录页。
     */
    if (!_data) {
      alert('没有权限,请重新登录');
    }

  }

  componentDidMount() {

  }

  componentWillUnmount() {

  }

}