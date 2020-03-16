import React, {Component} from "react";
import {Tabs} from "antd";
import NewProgramming from "../programming/NewProgramming";
import NewTest from "../test/NewTest";

const TabPane = Tabs.TabPane;

class Admin extends Component {
    render() {
        const tabBarStyle = {
            textAlign: 'center'
        };

        if (!this.props.isAuthenticated) {
            return (
                <h1 className="formItem">
                    Please login to use the webapp
                </h1>
            );
        }

        return (
            <div className="container">
                <Tabs defaultActiveKey="1"
                      animated={false}
                      tabBarStyle={tabBarStyle}
                      size="large"
                      className="profile-tabs">
                    <TabPane tab={`New programming competition`} key="1">
                        <NewProgramming />
                    </TabPane>
                    <TabPane tab={`New test`}  key="2">
                        <NewTest />
                    </TabPane>
                </Tabs>
            </div>
        );
    }
}

export default Admin;
