import React, { Component } from 'react';
import {addProgrammingResult, getAllProgrammings} from "../util/APIUtils";
import {Form, Select, Input, Button, notification} from 'antd';
import FormItem from "antd/es/form/FormItem";
const { TextArea } = Input;
const { Option } = Select;

class NewProgrammingResult extends Component {
    constructor(props) {
        super(props);
        this.state = {
            programmings: [],
            selectedProgramming: "",
            validationError: "",
            result: "",
            description: "Competition description"
        };

        this.handleResultChange = this.handleResultChange.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.isFormInvalid = this.isFormInvalid.bind(this);
    }

    componentDidMount() {
        getAllProgrammings()
            .then(data => {
                let gamesFromAPI = data.map(programming => {
                    return { value: programming.id, display: programming.name};
                });
                this.setState({
                    programmings: [{
                        value: 0,
                        display: "(Select a programming competition)"
                    }].concat(gamesFromAPI)
                });
            })
            .catch(error => {
                console.log(error);
            });
    }

    handleChange(value) {
        if (!value.toString().includes("Select a programming competition")) {
            this.setState({
                selectedProgramming: value
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
            programmingName: this.state.selectedProgramming,
            score: this.state.result
        };

        addProgrammingResult(resultData)
            .then(response => {
                this.props.history.push("/");
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
        if (this.state.selectedProgramming.length === 0) {
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
            <div className="programming-container">
                <h1 className="page-title">New result</h1>
                <div className="programming-content">
                    <Form onSubmit={this.handleSubmit} className="create-programming-form">
                        <FormItem>
                            <Select
                                value={this.state.selectedProgramming}
                                onSelect={(value, key) => this.handleChange(value, key)}
                            >
                                {this.state.programmings.map(programming => (
                                    <Option
                                        key = {programming.value}
                                        value = {programming.display}
                                    >
                                        {programming.display}
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
                        <FormItem>
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

export default NewProgrammingResult;
