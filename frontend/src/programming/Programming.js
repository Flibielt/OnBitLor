import React, { Component } from 'react';

class Programming extends Component {
    render() {
        return (
            <div className="content">
                <div className="programming-info">
                    <h2 className="name">
                        {this.props.programming.name}
                    </h2>
                    <p className="description">
                        {this.props.programming.description}
                    </p>
                    <p className="description">
                        Bit: {this.props.programming.bit}
                    </p>
                </div>
            </div>
        );
    }
}

export default Programming;
