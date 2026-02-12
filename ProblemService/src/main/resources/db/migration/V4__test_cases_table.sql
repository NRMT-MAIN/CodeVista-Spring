CREATE TABLE test_cases (
    id BIGINT PRIMARY KEY,
    input TEXT NOT NULL,
    expected_output TEXT NOT NULL,
    is_sample BOOLEAN DEFAULT FALSE,
    problem_id BIGINT NOT NULL,
    CONSTRAINT fk_testcase_problem FOREIGN KEY (problem_id) REFERENCES problems(id)
);

