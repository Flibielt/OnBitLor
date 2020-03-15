import React, { Component } from 'react';

class Test extends Component {
    render() {
        return (
            <div className="content">
                <div className="test-info">
                    <h2 className="name">
                        {this.props.test.name}
                    </h2>
                    <p className="description">
                        {this.props.test.description}
                    </p>
                    <p className="description">
                        Bit: {this.props.test.bit}
                    </p>
                </div>
            </div>
        );
    }
}

export default Test;
