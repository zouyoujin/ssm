import React from 'react';

import './../../css/App.less';

class Page extends React.Component {
    render() {
        return (
            <div style={{ height: '100%' }}>
                {this.props.children}
            </div>
        )
    }
}

export default Page;