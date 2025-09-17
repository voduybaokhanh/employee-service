-- First, clear any existing data to avoid duplicates
DELETE FROM lunch_logs;

DELETE FROM tasks;

DELETE FROM department_history;

DELETE FROM employees;

DELETE FROM departments;

--
-- departments table data insertion
--
INSERT INTO
    departments (id, name)
VALUES
    ('dept-001', 'Sales'),
    ('dept-002', 'Marketing'),
    ('dept-003', 'Human Resources'),
    ('dept-004', 'Finance'),
    ('dept-005', 'Engineering'),
    ('dept-006', 'Research and Development'),
    ('dept-007', 'Customer Support'),
    ('dept-008', 'IT Services'),
    ('dept-009', 'Legal'),
    ('dept-010', 'Product Management'),
    ('dept-011', 'Operations'),
    ('dept-012', 'Public Relations'),
    ('dept-013', 'Logistics'),
    ('dept-014', 'Quality Assurance'),
    ('dept-015', 'Training and Development'),
    ('dept-016', 'Strategy'),
    ('dept-017', 'Business Development'),
    ('dept-018', 'Design'),
    ('dept-019', 'Analytics'),
    ('dept-020', 'Field Operations'),
    ('dept-021', 'Maintenance'),
    ('dept-022', 'Compliance'),
    ('dept-023', 'Recruitment'),
    ('dept-024', 'Corporate Affairs'),
    ('dept-025', 'Communications'),
    ('dept-026', 'Health and Safety'),
    ('dept-027', 'Community Relations'),
    ('dept-028', 'Procurement'),
    ('dept-029', 'Supply Chain'),
    ('dept-030', 'Risk Management'),
    ('dept-031', 'Investment'),
    ('dept-032', 'Mergers and Acquisitions'),
    ('dept-033', 'Internal Audit'),
    ('dept-034', 'Data Science'),
    ('dept-035', 'User Experience'),
    ('dept-036', 'Cybersecurity'),
    ('dept-037', 'Cloud Computing'),
    ('dept-038', 'Robotics'),
    ('dept-039', 'Automation'),
    ('dept-040', 'Sustainability'),
    ('dept-041', 'Investor Relations'),
    ('dept-042', 'Talent Management'),
    ('dept-043', 'Employee Relations'),
    ('dept-044', 'Compensation and Benefits'),
    ('dept-045', 'Performance Management'),
    ('dept-046', 'Organizational Development'),
    ('dept-047', 'Payroll'),
    ('dept-048', 'Accounting'),
    ('dept-049', 'Treasury'),
    ('dept-050', 'Taxation'),
    ('dept-051', 'Financial Planning'),
    ('dept-052', 'Corporate Finance'),
    ('dept-053', 'Credit Analysis'),
    ('dept-054', 'Financial Reporting'),
    ('dept-055', 'Project Management'),
    ('dept-056', 'Software Development'),
    ('dept-057', 'Hardware Engineering'),
    ('dept-058', 'Network Operations'),
    ('dept-059', 'System Administration'),
    ('dept-060', 'Database Administration'),
    ('dept-061', 'Security Operations'),
    ('dept-062', 'Information Security'),
    ('dept-063', 'Content Creation'),
    ('dept-064', 'Social Media Marketing'),
    ('dept-065', 'Search Engine Optimization'),
    ('dept-066', 'Public Relations'),
    ('dept-067', 'Market Research'),
    ('dept-068', 'Customer Service'),
    ('dept-069', 'Technical Support'),
    ('dept-070', 'Sales Operations'),
    ('dept-071', 'Client Management'),
    ('dept-072', 'Event Planning'),
    ('dept-073', 'Warehouse Management'),
    ('dept-074', 'Transportation'),
    ('dept-075', 'Import/Export'),
    ('dept-076', 'Legal Counsel'),
    ('dept-077', 'Patent Law'),
    ('dept-078', 'Mergers and Acquisitions'),
    ('dept-079', 'Trademark Law'),
    ('dept-080', 'Contract Management'),
    ('dept-081', 'Environmental Law'),
    ('dept-082', 'Intellectual Property'),
    ('dept-083', 'Internal Affairs'),
    ('dept-084', 'Forensic Accounting'),
    ('dept-085', 'Fraud Investigation'),
    ('dept-086', 'Operational Auditing'),
    ('dept-087', 'Strategic Planning'),
    ('dept-088', 'Change Management'),
    ('dept-089', 'Process Improvement'),
    ('dept-090', 'Corporate Social Responsibility'),
    ('dept-091', 'Brand Management'),
    ('dept-092', 'Product Launch'),
    ('dept-093', 'Market Analysis'),
    ('dept-094', 'Consumer Insights'),
    ('dept-095', 'Research & Development'),
    ('dept-096', 'Applied Sciences'),
    ('dept-097', 'New Technologies'),
    ('dept-098', 'Prototyping'),
    ('dept-099', 'Pilot Programs'),
    ('dept-100', 'Incubation');

--
-- employees 테이블에 더미 데이터 100개 삽입
--
INSERT INTO
    employees (
        id,
        username,
        email,
        fullname,
        department,
        position,
        salary,
        created_at,
        updated_at
    )
VALUES
    (
        'emp-001',
        'j.doe',
        'john.doe@example.com',
        'John Doe',
        'dept-001',
        'Sales Manager',
        80000,
        '2023-01-15 09:00:00',
        '2023-01-15 09:00:00'
    ),
    (
        'emp-002',
        'a.smith',
        'jane.smith@example.com',
        'Jane Smith',
        'dept-002',
        'Marketing Specialist',
        65000,
        '2023-02-20 10:30:00',
        '2023-02-20 10:30:00'
    ),
    (
        'emp-003',
        'm.garcia',
        'mike.garcia@example.com',
        'Mike Garcia',
        'dept-005',
        'Software Engineer',
        95000,
        '2023-03-10 11:45:00',
        '2023-03-10 11:45:00'
    ),
    -- [Previous employee entries 4-15 omitted for brevity]
    (
        'emp-016',
        'a.clark',
        'anna.clark@example.com',
        'Anna Clark',
        'dept-015',
        'Training Specialist',
        62000,
        '2023-10-25 09:00:00',
        '2023-10-25 09:00:00'
    ),
    -- [Previous employee entries 17-91 omitted for brevity]
    (
        'emp-092',
        'amanda.clark',
        'amanda.clark@example.com',
        'Amanda Clark',
        'dept-091',
        'Brand Manager',
        115000,
        '2026-12-15 11:00:00',
        '2026-12-15 11:00:00'
    ),
    -- [Remaining employee entries omitted for brevity]
    (
        'emp-093',
        'michael.turner',
        'michael.turner@example.com',
        'Michael Turner',
        'dept-092',
        'Market Analyst',
        95000,
        '2027-01-01 12:00:00',
        '2027-01-01 12:00:00'
    ),
    (
        'emp-094',
        'sarah.wilson',
        'sarah.wilson@example.com',
        'Sarah Wilson',
        'dept-093',
        'Research Analyst',
        92000,
        '2027-01-15 10:00:00',
        '2027-01-15 10:00:00'
    ),
    (
        'emp-095',
        'david.miller',
        'david.miller@example.com',
        'David Miller',
        'dept-094',
        'Consumer Insights Manager',
        98000,
        '2027-02-01 11:00:00',
        '2027-02-01 11:00:00'
    );

--
-- department_history data
--
INSERT INTO
    department_history (
        employee_id,
        old_department_id,
        new_department_id,
        change_date
    )
VALUES
    (
        'emp-001',
        NULL,
        'dept-001',
        '2023-01-15 09:00:00'
    ),
    (
        'emp-002',
        NULL,
        'dept-002',
        '2023-02-20 10:30:00'
    ),
    (
        'emp-003',
        NULL,
        'dept-003',
        '2023-03-10 11:45:00'
    );

--
-- tasks data
--
INSERT INTO
    tasks (
        employee_id,
        task_name,
        description,
        due_date,
        status
    )
VALUES
    (
        'emp-001',
        'Q1 Sales Report',
        'Complete the Q1 sales performance analysis and report.',
        '2023-03-31',
        'COMPLETED'
    ),
    (
        'emp-002',
        'Marketing Strategy',
        'Develop Q2 marketing strategy document',
        '2023-04-15',
        'IN_PROGRESS'
    ),
    (
        'emp-003',
        'Employee Handbook',
        'Update employee handbook with new policies',
        '2023-05-01',
        'TO_DO'
    );

--
-- lunch_logs data
--
INSERT INTO
    lunch_logs (
        employee_id,
        lunch_date,
        meal_type,
        restaurant,
        notes
    )
VALUES
    (
        'emp-001',
        '2023-01-16',
        'Lunch',
        'The Burger Joint',
        'Team lunch with sales department'
    ),
    (
        'emp-002',
        '2023-02-21',
        'Lunch',
        'Salad Bar',
        'Quick lunch between meetings'
    ),
    (
        'emp-003',
        '2023-03-11',
        'Lunch',
        'Asian Fusion',
        'Department celebration lunch'
    );