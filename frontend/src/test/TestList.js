import React, { Component } from 'react';
import { getAllTests } from '../util/APIUtils';
import LoadingIndicator  from '../common/LoadingIndicator';
import { PROGRAMMING_LIST_SIZE } from '../constants';
import { withRouter } from 'react-router-dom';
import Test from "./Test";

class TestList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tests: [],
            page: 0,
            size: 10,
            totalElements: 0,
            totalPages: 0,
            last: true,
            isLoading: false
        };
        this.loadTestList = this.loadTestList.bind(this);
        this.handleLoadMore = this.handleLoadMore.bind(this);
    }

    loadTestList(page = 0, size = PROGRAMMING_LIST_SIZE) {
        let promise = getAllTests(page, size);

        if (!promise) {
            return null;
        }

        this.setState({
            isLoading: true
        });

        promise.then(response => {
            const tests = this.state.tests.slice();

            this.setState({
                tests: tests.concat(response),
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
        this._mounted = true;
        this.loadTestList();
    }

    componentWillUnmount () {
        this._mounted = false
    }

    setState(state, callback) {
        if (this._mounted) {
            super.setState(state, callback);
        }
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if(this.props.isAuthenticated !== prevProps.isAuthenticated) {
            // Reset State
            this.setState({
                tests: [],
                page: 0,
                size: 10,
                totalElements: 0,
                totalPages: 0,
                last: true,
                currentVotes: [],
                isLoading: false
            });
            this.loadTestList();
        }
    }

    handleLoadMore() {
        this.loadTestList(this.state.page + 1);
    }

    render() {
        const testViews = [];
        this.state.tests.forEach((test, testIndex) =>{
            testViews.push(<Test
                key={test.id}
                test={test}
            />)
        });

        return (
            <div className="tests-container">
                <h1 className="title">
                    Programming competitions
                </h1>
                {testViews}
                {
                    !this.state.isLoading && this.state.tests.length === 0 ? (
                        <div className="no-tests-found">
                            <span>No Tests Found.</span>
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

export default withRouter(TestList);
