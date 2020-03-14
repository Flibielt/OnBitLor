import React, { Component } from 'react';
import { getAllProgrammings } from '../util/APIUtils';
import Programming from './Programming';
import LoadingIndicator  from '../common/LoadingIndicator';
import { Button, Icon, notification } from 'antd';
import { PROGRAMMING_LIST_SIZE } from '../constants';
import { withRouter } from 'react-router-dom';
import './ProgrammingList.css';

class ProgrammingList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            programmings: [],
            page: 0,
            size: 10,
            totalElements: 0,
            totalPages: 0,
            last: true,
            isLoading: false
        };
        this.loadProgrammingList = this.loadProgrammingList.bind(this);
        this.handleLoadMore = this.handleLoadMore.bind(this);
    }

    loadProgrammingList(page = 0, size = PROGRAMMING_LIST_SIZE) {
        let promise = getAllProgrammings(page, size);

        if (!promise) {
            return null;
        }

        this.setState({
            isLoading: true
        });

        promise.then(response => {
            const programmings = this.state.programmings.slice();
            console.log(response);

            this.setState({
                programmings: programmings.concat(response),
                page: response.page,
                size: response.size,
                totalElements: response.totalElements,
                totalPages: response.totalPages,
                last: response.last,
                isLoading: false
            })
        }).catch(error => {
            this.setState({
                isLoading: false
            })
        })
    }

    componentDidMount() {
        this.loadProgrammingList();
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if(this.props.isAuthenticated !== prevProps.isAuthenticated) {
            // Reset State
            this.setState({
                polls: [],
                page: 0,
                size: 10,
                totalElements: 0,
                totalPages: 0,
                last: true,
                currentVotes: [],
                isLoading: false
            });
            this.loadProgrammingList();
        }
    }

    handleLoadMore() {
        this.loadProgrammingList(this.state.page + 1);
    }

    handleViewStat(event, programmingIndex) {
        event.preventDefault();
        if(!this.props.isAuthenticated) {
            this.props.history.push("/login");
            notification.info({
                message: 'Bit Calculator App',
                description: "Please login to view your statistic.",
            });
            return;
        }

        const programming = this.state.programmings[programmingIndex];
        this.props.history.push("/prog/me/stat/" + programming.id);
    }

    handleNewResult(event, programmingIndex) {
        event.preventDefault();
        if(!this.props.isAuthenticated) {
            this.props.history.push("/login");
            notification.info({
                message: 'Bit Calculator App',
                description: "Please login to add new result.",
            });
            return;
        }

        const programming = this.state.programmings[programmingIndex];
        this.props.history.push("/prog/me/new/" + programming.id);
    }

    render() {
        const programmingViews = [];
        this.state.programmings.forEach((programming, programmingIndex) =>{
            programmingViews.push(<Programming
                key={programming.id}
                programming={programming}
                handleViewStat={(event) => this.handleViewStat(event, programmingIndex)}
                handleNewResult={(event) => this.handleNewResult(event, programmingIndex)}
            />)
        });

        return (
            <div className="programmings-container">
                <h1 className="title">
                    Programming competitions
                </h1>
                {programmingViews}
                {
                    !this.state.isLoading && this.state.programmings.length === 0 ? (
                        <div className="no-programmings-found">
                            <span>No Programmings Found.</span>
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

export default withRouter(ProgrammingList);
