import React, { Component } from 'react';
import './Programming.css';

import { Button } from 'antd';

class Programming extends Component {
    render() {
        return (
            <div className="programming-content">
                <div className="programming-info">
                    <h2 className="programming-name">
                        {this.props.programming.name}
                    </h2>
                    <p className="programming-description">
                        {this.props.programming.description}
                    </p>
                    <p className="programming-description">
                        Bit: {this.props.programming.bit}
                    </p>
                </div>
            </div>
        );
    }
}

export default Programming;
