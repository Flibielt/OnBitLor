import React, { Component } from 'react';
import './Programming.css';

import { Button } from 'antd';

class Programming extends Component {
    render() {
        return (
            <div className="programming-content">
                <div className="programming-info">
                    <div className="programming-name">
                        {this.props.programming.name}
                    </div>
                    <div className="programming-description">
                        {this.props.programming.description}
                    </div>
                </div>
                <div className="programming-footer">
                    <Button className="prog-button" onClick={this.props.handleViewStat}>
                        View Statistic
                    </Button>
                    <Button className="programming-button" onClick={this.props.handleNewResult}>
                        Add new result
                    </Button>
                </div>
            </div>
        );
    }
}

export default Programming;
