import React, { Component } from 'react';

class UserInfo extends Component {
    render() {
        return (
            <div className="content">
                <div className="user-info">
                    <h2 className="name">
                        {this.props.user.username}
                    </h2>
                    <p className="description">
                        {this.props.user.firstName} {this.props.user.lastName}
                    </p>
                    <p className="description">
                        Bit: {this.props.user.bit}
                    </p>
                </div>
            </div>
        );
    }
}

export default UserInfo;
