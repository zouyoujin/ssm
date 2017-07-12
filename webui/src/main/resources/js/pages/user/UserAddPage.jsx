import React, { Component } from 'react';
import { DatePicker } from 'antd';

export default class UserAddPage extends Component {

  constructor(prop) {
    super(prop);
  }
  
  render() {
    return (
      <div style={{ width: 500, margin: '100px auto' }}>
        <DatePicker />
        <div style={{ marginTop: 20 }}>当前日期11Test：</div>
      </div>
    );
  }
}