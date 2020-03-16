import React, { Component } from "react";
import {formatDateTime} from "../util/Helpers";

class ProgrammingResult extends Component{
    render() {
        return (
            <div className="content">
                <div className="position">
                    <h2 className="title">
                        {this.props.position}.
                    </h2>
                </div>
                <div className="summary">
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
                        {formatDateTime(this.props.result.date)}
                    </p>
                </div>
            </div>
        );
    }
}

export default ProgrammingResult;
