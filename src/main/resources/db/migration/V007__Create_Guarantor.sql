CREATE TABLE guarantor
(
    guarantor_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    member_id    BIGINT,
    confirmed    VARCHAR(255),
    loan_id      BIGINT,
    CONSTRAINT pk_guarantor PRIMARY KEY (guarantor_id)
);

ALTER TABLE guarantor
    ADD CONSTRAINT FK_GUARANTOR_ON_LOAN FOREIGN KEY (loan_id) REFERENCES loan (loan_id);

ALTER TABLE guarantor
    ADD CONSTRAINT FK_GUARANTOR_ON_MEMBER FOREIGN KEY (member_id) REFERENCES member (member_id);