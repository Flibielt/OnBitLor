import React, { Component } from "react";
import {getAllTestResult, getAllTests} from "../util/APIUtils";
import {Select, notification, Form} from "antd";
import TestResult from "./TestResult";
import LoadingIndicator from "../common/LoadingIndicator";
import FormItem from "antd/es/form/FormItem";
const { Option } = Select;

class TestResultList extends Component{
    constructor(props) {
        super(props);
        this.state = {
            tests: [],
            results: [],
            resultViews: [],
            selectedTest: '',
            isLoading: false
        };
        this.loadTestResultList = this.loadTestResultList.bind(this);
        this.handleTestChange = this.handleTestChange.bind(this);
    }

    loadTestResultList(testName) {
        this.setState({
            resultViews: [],
            results: []
        });

        let promise = getAllTestResult(testName);

        if (!promise) {
            return null;
        }

        this.setState({
            isLoading: true
        });

        promise.then(response => {
            const results = this.state.results.slice();

            this.setState({
                results: results.concat(response),
                isLoading: false
            });
            let resultViews = [];
            resultViews.length = 0;
            let position = 0;
            this.state.results.forEach((result) => {
                position++;
                resultViews.push(<TestResult
                    key={result.id}
                    result={result}
                    position={position}
                />)
            });
            this.setState({
                resultViews: resultViews
            });
            if (position === 0) {
                this.setState({
                    resultViews: <h1>No results found</h1>
                })
            }
        }).catch(error => {
            this.setState({
                isLoading: false
            });
            notification.error({
                message: 'Bit Calculator App',
                description: error.message
            });
        });
    }

    handleTestChange(value) {
        if (!value.toString().includes("Select a test")) {
            this.setState({
                selectedTest: value
            });
            this.loadTestResultList(value);
        }
    }

    componentDidMount() {
        getAllTests()
            .then(data => {
                let tests = data.map(test => {
                    return { value: test.id, display: test.name};
                });
                this.setState({
                    tests: [{
                        value: 0,
                        display: "Select a test"
                    }].concat(tests)
                });
            })
            .catch(error => {
                notification.error({
                    message: 'Bit Calculator App',
                    description: error.message
                });
            });
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if(this.props.isAuthenticated !== prevProps.isAuthenticated) {
            // Reset State
            this.setState({
                tests: [],
                results: [],
                selectedTest: '',
                isLoading: false
            });
        }
    }

    render() {
        return (
            <div className="container">
                <Form>
                    <FormItem>
                        <Select style={{fontSize: "large"}}
                                value={this.state.selectedTest}
                                onSelect={(value, key) => this.handleTestChange(value, key)}>
                            {this.state.tests.map(test => (
                                <Option style={{fontSize: "large"}}
                                        key={test.value}
                                        value={test.display}>
                                    {test.display}
                                </Option>
                            ))}
                        </Select>
                    </FormItem>
                </Form>
                {this.state.resultViews}
                {
                    !this.state.isLoading && this.state.tests.length === 0 ? (
                        <div className="nothing-found">
                            <span>No Test results Found.</span>
                        </div>
                    ): null
                }
                {
                    this.state.isLoading ?
                        <LoadingIndicator />: null
                }
            </div>
        );
    }

}

export default TestResultList;
