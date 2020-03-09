import React, { Component } from 'react';
import {getAllGames, getAllProgrammings} from "../util/APIUtils";
import {Form, Select} from 'antd';
import FormItem from "antd/es/form/FormItem";
const { Option } = Select;

class NewProgrammingResult extends Component {
    constructor(props) {
        super(props);
        this.state = {
            programmings: [],
            selectedProgramming: "",
            validationError: ""
        }
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

    handleChange(value, event) {
        console.log(`selected ${value}`);
        console.log(`Event ${event.id}`);
        this.setState({
            selectedProgramming: value
        });
    }

    handleSubmit(event) {
        event.preventDefault();
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
                                {this.state.selectedProgramming}
                            </div>
                        </FormItem>

                    </Form>
                </div>
            </div>
        )
    }
}

export default NewProgrammingResult;
