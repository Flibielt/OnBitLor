import React, { Component } from "react";

class ProgrammingResult extends Component{
    render() {
        return (
            <div className="content">
                <h2 className="title">
                    Result: {this.props.result.score}
                </h2>
                <p className="description">
                    {this.props.result.username}
                </p>
                <p className="description">
                    {this.props.result.firstName} {this.props.result.lastName}
                </p>
                <p className="description">
                    {this.props.result.date}
                </p>
            </div>
        );
    }
}

export default ProgrammingResult;
