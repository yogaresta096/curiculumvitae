import React from 'react';
import { Container } from '@mui/material';
import ExperiencesCard from '../components/ExperiencesCard';
import { useParams } from 'react-router-dom';

function Experiences() {
    const { personalId } = useParams();

    return (
        <Container 
            maxWidth="sm" 
            style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}
        >
            <ExperiencesCard />
        </Container>
    );
}

export default Experiences;
