import React, { useEffect, useState } from 'react';
import { Container, Card, CardContent, Typography, CircularProgress } from '@mui/material';
import { getPersonalById } from '../services/PersonalService';
import HomeAppBar from '../components/HomeAppBar';
import { useNavigate } from 'react-router-dom';
import { PERSONAL_ID } from '../config/ConfigGetData';

function Home() {
    const [personalData, setPersonalData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    const personalId = PERSONAL_ID;

    useEffect(() => {
        const fetchPersonalData = async () => {
            setLoading(true);
            setError(null);
            try {
                const data = await getPersonalById(personalId);
                setPersonalData(data);
            } catch (err) {
                setError("Connection is failure " + err);
                console.error("Error:", err);
            } finally {
                setLoading(false);
            }
        };

        fetchPersonalData();
    }, [personalId]);

    const handleNavigateToExperiences = () => {
        navigate(`/experiences`);
    };

    return (
        <Container 
            maxWidth="sm" 
            style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}
        >
            <Card 
                style={{ width: '100%', maxWidth: '100%', borderRadius: '16px' }} 
                elevation={3}
                onClick={handleNavigateToExperiences}
            >
                <CardContent>
                    {loading ? (
                        <CircularProgress style={{ alignItems: 'center' }} />
                    ) : error ? (
                        <Typography variant="body2" color="error">
                            {error}
                        </Typography>
                    ) : personalData ? (
                        <Typography variant="h5" component="div" style={{ textAlign: 'center', fontFamily: 'Poppins, sans-serif' }}>
                            Hi I'm {personalData.firstName} {personalData.lastName}
                        </Typography>
                    ) : (
                        <Typography variant='body2' color='text.secondary'>
                            Data Not Found
                        </Typography>
                    )}
                </CardContent>
                <HomeAppBar />
            </Card>
        </Container>
    );
}

export default Home;