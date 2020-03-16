import React, { Component } from "react";
import {getAllProgrammingResult, getAllProgrammings} from "../util/APIUtils";
import {Select, notification, Form} from "antd";
import ProgrammingResult from "./ProgrammingResult";
import LoadingIndicator from "../common/LoadingIndicator";
import FormItem from "antd/es/form/FormItem";
const { Option } = Select;

class ProgrammingResultList extends Component{
    constructor(props) {
        super(props);
        this.state = {
            competitions: [],
            results: [],
            resultViews: [],
            selectedCompetition: '',
            isLoading: false
        };
        this.loadProgrammingResultList = this.loadProgrammingResultList.bind(this);
        this.handleCompetitionChange = this.handleCompetitionChange.bind(this);
    }

    loadProgrammingResultList(programmingName) {
        this.setState({
            resultViews: [],
            results: []
        });

        let promise = getAllProgrammingResult(programmingName);

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
                resultViews.push(<ProgrammingResult
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

    handleCompetitionChange(value) {
        if (!value.toString().includes("Select a programming competition")) {
            this.setState({
                selectedCompetition: value
            });
            this.loadProgrammingResultList(value);
        }
    }

    componentDidMount() {
        getAllProgrammings()
            .then(data => {
                let competitions = data.map(competition => {
                    return { value: competition.id, display: competition.name};
                });
                this.setState({
                    competitions: [{
                        value: 0,
                        display: "Select a programming competition"
                    }].concat(competitions)
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
                competitions: [],
                results: [],
                selectedCompetition: '',
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
                            value={this.state.selectedCompetition}
                            onSelect={(value, key) => this.handleCompetitionChange(value, key)}>
                            {this.state.competitions.map(competition => (
                                <Option style={{fontSize: "large"}}
                                    key={competition.value}
                                    value={competition.display}>
                                    {competition.display}
                                </Option>
                            ))}
                        </Select>
                    </FormItem>
                </Form>
                {this.state.resultViews}
                {
                    !this.state.isLoading && this.state.competitions.length === 0 ? (
                        <div className="nothing-found">
                            <span>No Programming results Found.</span>
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

export default ProgrammingResultList;
