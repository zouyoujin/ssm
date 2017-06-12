import React from 'react';

class Page extends React.Component<any, any> {
    render() {
        return (
            <div style={{ height: '100%' }}>
                {this.props.children}
            </div>
        )

    }
}

export default Page;