import React, { Component } from 'react';
import {addTestResult, getAllTestResult} from "../util/APIUtils";
import {Form, Select, Input, Button, notification} from 'antd';
import FormItem from "antd/es/form/FormItem";
import {withRouter} from "react-router-dom";
const { TextArea } = Input;
const { Option } = Select;

class NewTestResult extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tests: [],
            selectedTest: "",
            validationError: "",
            result: "",
            description: "test description"
        };

        this.handleResultChange = this.handleResultChange.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.isFormInvalid = this.isFormInvalid.bind(this);
    }

    componentDidMount() {
        getAllTestResult()
            .then(data => {
                let testsFromAPI = data.map(test => {
                    return { value: test.id, display: test.name};
                });
                this.setState({
                    programmings: [{
                        value: 0,
                        display: "(Select a test)"
                    }].concat(testsFromAPI)
                });
            })
            .catch(error => {
                console.log(error);
            });
    }

    handleChange(value) {
        if (!value.toString().includes("Select a test")) {
            this.setState({
                selectedTest: value
            });
        }
    }

    handleResultChange(event) {
        if (event.target.value.length < 20) {
            this.setState({
                result: event.target.value
            })
        }
    }

    handleSubmit(event) {
        event.preventDefault();
        const resultData = {
            testName: this.state.selectedTest,
            score: this.state.result
        };

        addTestResult(resultData)
            .then(response => {
                this.props.history.push("/");
                notification.success({
                    message: 'Bit calculator App',
                    description: response.message || 'Saved successfully'
                })
            }).catch(error => {
            if (error.status === 401) {
                this.props.handleLogout('/login', 'error', 'You have been logged out. Please login to add a new result.');
            } else {
                notification.error({
                    message: 'Bit Calculator App',
                    description: error.message || 'Sorry! Something went wrong. Please try again!'
                });
            }
        })
    }

    isFormInvalid() {
        if (this.state.selectedTest.length === 0) {
            return true;
        }
        if (this.state.result.length === 0) {
            return true;
        }
        if (isNaN(this.state.result)) {
            return true;
        }
        return this.state.result <= 0;
    }

    render() {
        return (
            <div className="container">
                <h1 className="page-title">New result</h1>
                <div className="test-content">
                    <Form onSubmit={this.handleSubmit} className="create-test-form">
                        <FormItem>
                            <Select
                                value={this.state.selectedTest}
                                onSelect={(value, key) => this.handleChange(value, key)}>
                                {this.state.test.map(test => (
                                    <Option
                                        key = {test.value}
                                        value = {test.display}>
                                        {test.display}
                                    </Option>
                                ))}
                            </Select>
                            <div>
                                {this.state.validationError}
                            </div>
                            <div>
                                {this.state.description}
                            </div>
                        </FormItem>
                        <FormItem>
                            <TextArea
                                style={{resize: "none"}}
                                placeholder="Enter your result"
                                name="result"
                                autoSize={{ minRows: 1, maxRows: 1 }}
                                value={this.state.result}
                                onChange={this.handleResultChange} />
                        </FormItem>
                        <FormItem style={{textAlign: "center"}}>
                            <Button type="primary"
                                    htmlType="submit"
                                    disabled={this.isFormInvalid()}
                                    size="large">
                                Add result
                            </Button>
                        </FormItem>
                    </Form>
                </div>
            </div>
        )
    }
}

export default withRouter(NewTestResult);
