import React, {Component} from "react";
import {Tabs} from "antd";
import ProgrammingResultList from "../programming/ProgrammingResultList";
import TestResultList from "../test/TestResultList";

const TabPane = Tabs.TabPane;

class Statistics extends Component {
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
                    <TabPane tab={`Programming results`} key="1">
                        <ProgrammingResultList />
                    </TabPane>
                    <TabPane tab={`Test results`}  key="2">
                        <TestResultList />
                    </TabPane>
                </Tabs>
            </div>
        );
    }
}

export default Statistics;
