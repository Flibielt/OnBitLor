import React, {Component} from "react";
import {Button, Form, Input, notification} from "antd";
import FormItem from "antd/es/form/FormItem";
import {withRouter} from "react-router-dom";
import {addNewTest, checkTestNameAvailability} from "../util/APIUtils";
import TextArea from "antd/es/input/TextArea";

class NewTest extends Component {
    constructor(props) {
        super(props);
        this.state = {
            test: {
                value: ''
            },
            description: {
                value: ''
            },
            bit: {
                value: ''
            }
        };

        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.validateTestAvailability = this.validateTestAvailability.bind(this);
        this.isFormInvalid = this.isFormInvalid.bind(this);
    }

    handleInputChange(event, validationFun) {
        const target = event.target;
        const inputName = target.name;
        const inputValue = target.value;

        this.setState({
            [inputName] : {
                value: inputValue,
                ...validationFun(inputValue)
            }
        });
    }

    handleSubmit(event) {
        event.preventDefault();

        const newProgramming = {
            name: this.state.competition.value,
            description: this.state.description.value,
            bit: this.state.bit.value
        };
        addNewTest(newProgramming)
            .then(response => {
                notification.success({
                    message: 'Bit Calculator App',
                    description: "Test saved successfully",
                });
                this.props.history.push("/");
            }).catch(error => {
            notification.error({
                message: 'Bit Calculator App',
                description: error.message || 'Sorry! Something went wrong. Please try again!'
            });
        })
    }

    isFormInvalid() {
        return !(this.state.test.validateStatus === 'success' &&
            this.state.description.validateStatus === 'success' &&
            this.state.bit.validateStatus === 'success'
        );
    }

    render() {
        return (
            <div className="container">
                <h1>
                    Add new programming competition
                </h1>
                <Form onSubmit={this.handleSubmit}>
                    <FormItem
                        label="Name"
                        validateStatus={this.state.test.validateStatus}
                        help={this.state.test.errorMsg}>
                        <Input
                            size="large"
                            name="test"
                            autoComplete="off"
                            placeholder="The test name"
                            value={this.state.test.value}
                            onBlur={this.validateTestAvailability}
                            onChange={(event) => this.handleInputChange(event, this.validateTest)} />
                    </FormItem>
                    <FormItem
                        label="Description"
                        validateStatus={this.state.description.validateStatus}
                        help={this.state.description.errorMsg}>
                        <TextArea
                            size="large"
                            name="description"
                            autoComplete="off"
                            placeholder="The description of the test"
                            rows={4}
                            style={{resize: "none"}}
                            value={this.state.description.value}
                            onChange={(event) => this.handleInputChange(event, this.validateDescription)} />
                    </FormItem>
                    <FormItem
                        label="Bit"
                        validateStatus={this.state.bit.validateStatus}
                        help={this.state.bit.errorMsg}>
                        <Input
                            size="large"
                            name="bit"
                            autoComplete="off"
                            placeholder="The bit reward of the test"
                            value={this.state.bit.value}
                            onChange={(event) => this.handleInputChange(event, this.validateBit)} />
                    </FormItem>
                    <FormItem className="formItem">
                        <Button type="primary"
                                htmlType="submit"
                                disabled={this.isFormInvalid()}
                                size="large">
                            Add new competition
                        </Button>
                    </FormItem>
                </Form>
            </div>
        );
    }

    validateTest = (test) => {
        if(test.length < 3) {
            return {
                validateStatus: 'error',
                errorMsg: `Test name is too short (Minimum 3 characters needed.)`
            }
        } else if (test.length > 50) {
            return {
                validationStatus: 'error',
                errorMsg: `Test name is too long (Maximum 50 characters allowed.)`
            }
        } else {
            return {
                validateStatus: 'success',
                errorMsg: null,
            };
        }
    };

    validateTestAvailability() {
        // First check for client side errors in competition
        const testValue = this.state.test.value;
        const testValidation = this.validateTest(testValue);

        if(testValidation.validateStatus === 'error') {
            this.setState({
                test: {
                    value: testValue,
                    ...testValidation
                }
            });
            return;
        }

        this.setState({
            test: {
                value: testValue,
                validateStatus: 'validating',
                errorMsg: null
            }
        });

        checkTestNameAvailability(testValue)
            .then(response => {
                if(response.isAvailable) {
                    this.setState({
                        competition: {
                            value: testValue,
                            validateStatus: 'success',
                            errorMsg: null
                        }
                    });
                } else {
                    this.setState({
                        competition: {
                            value: testValue,
                            validateStatus: 'error',
                            errorMsg: 'This test name is already taken'
                        }
                    });
                }
            }).catch(error => {
            // Marking validateStatus as success, Form will be rechecked at server
            this.setState({
                competition: {
                    value: testValue,
                    validateStatus: 'success',
                    errorMsg: null
                }
            });
        });
    }

    validateDescription = (description) => {
        if(description.length < 3) {
            return {
                validateStatus: 'error',
                errorMsg: `Description is too short (Minimum 3 characters needed.)`
            }
        } else if (description.length > 1000) {
            return {
                validationStatus: 'error',
                errorMsg: `Description is too long (Maximum 1000 characters allowed.)`
            }
        } else {
            return {
                validateStatus: 'success',
                errorMsg: null,
            };
        }
    };

    validateBit = (bit) => {
        if(bit.length < 1) {
            return {
                validateStatus: 'error',
                errorMsg: `Bit is too short (Minimum 1 characters needed.)`
            }
        } else if (bit.length > 20) {
            return {
                validationStatus: 'error',
                errorMsg: `Bit is too long (Maximum 20 characters allowed.)`
            }
        } else if (isNaN(bit)) {
            return {
                validationStatus: 'error',
                errorMsg: `Must be a number`
            }
        } else if (bit < 0) {
            return {
                validationStatus: 'error',
                errorMsg: `Must be bigger than 0`
            }
        } else {
            return {
                validateStatus: 'success',
                errorMsg: null,
            };
        }
    };
}

export default withRouter(NewTest);
