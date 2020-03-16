import React, {Component} from "react";
import {Tabs} from "antd";
import ProgrammingList from "../programming/ProgrammingList";
import TestList from "../test/TestList";
import UserInfoList from "../user/info/UserInfoList";

const TabPane = Tabs.TabPane;

class OverallList extends Component {
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
                    <TabPane tab={`Programming competitions`} key="1">
                        <ProgrammingList />
                    </TabPane>
                    <TabPane tab={`Tests`}  key="2">
                        <TestList />
                    </TabPane>
                    <TabPane tab={`Users`}  key="3">
                        <UserInfoList />
                    </TabPane>
                </Tabs>
            </div>
        );
    }
}

export default OverallList;
