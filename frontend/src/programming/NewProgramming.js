import React, {Component} from "react";
import {Button, Form, Input, notification} from "antd";
import FormItem from "antd/es/form/FormItem";
import {withRouter} from "react-router-dom";
import {addNewProgramming, checkProgrammingNameAvailability} from "../util/APIUtils";
import TextArea from "antd/es/input/TextArea";

class NewProgramming extends Component{
    constructor(props) {
        super(props);
        this.state = {
            competition: {
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
        this.validateCompetitionAvailability = this.validateCompetitionAvailability.bind(this);
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
        addNewProgramming(newProgramming)
            .then(response => {
                notification.success({
                    message: 'Bit Calculator App',
                    description: "Programming competition saved successfully",
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
        return !(this.state.competition.validateStatus === 'success' &&
            this.state.description.validateStatus === 'success' &&
            this.state.bit.validateStatus === 'success'
        );
    }

    render() {
        return (
            <div className="container">
                <Form onSubmit={this.handleSubmit}>
                    <FormItem
                        label="Name"
                        validateStatus={this.state.competition.validateStatus}
                        help={this.state.competition.errorMsg}>
                        <Input
                            size="large"
                            name="competition"
                            autoComplete="off"
                            placeholder="The competition name"
                            value={this.state.competition.value}
                            onBlur={this.validateCompetitionAvailability}
                            onChange={(event) => this.handleInputChange(event, this.validateCompetition)} />
                    </FormItem>
                    <FormItem
                        label="Description"
                        validateStatus={this.state.description.validateStatus}
                        help={this.state.description.errorMsg}>
                        <TextArea
                            size="large"
                            name="description"
                            autoComplete="off"
                            placeholder="The description of the competition"
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
                            placeholder="The bit reward of the competition"
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

    validateCompetition = (competition) => {
        if(competition.length < 3) {
            return {
                validateStatus: 'error',
                errorMsg: `Competition is too short (Minimum 3 characters needed.)`
            }
        } else if (competition.length > 50) {
            return {
                validationStatus: 'error',
                errorMsg: `Competition is too long (Maximum 50 characters allowed.)`
            }
        } else {
            return {
                validateStatus: 'success',
                errorMsg: null,
            };
        }
    };

    validateCompetitionAvailability() {
        // First check for client side errors in competition
        const competitionValue = this.state.competition.value;
        const competitionValidation = this.validateCompetition(competitionValue);

        if(competitionValidation.validateStatus === 'error') {
            this.setState({
                competition: {
                    value: competitionValue,
                    ...competitionValidation
                }
            });
            return;
        }

        this.setState({
            competition: {
                value: competitionValue,
                validateStatus: 'validating',
                errorMsg: null
            }
        });

        checkProgrammingNameAvailability(competitionValue)
            .then(response => {
                if(response.isAvailable) {
                    this.setState({
                        competition: {
                            value: competitionValue,
                            validateStatus: 'success',
                            errorMsg: null
                        }
                    });
                } else {
                    this.setState({
                        competition: {
                            value: competitionValue,
                            validateStatus: 'error',
                            errorMsg: 'This competition name is already taken'
                        }
                    });
                }
            }).catch(error => {
            // Marking validateStatus as success, Form will be rechecked at server
            this.setState({
                competition: {
                    value: competitionValue,
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

export default withRouter(NewProgramming);
