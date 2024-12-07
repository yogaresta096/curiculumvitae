import React, { useEffect, useState } from 'react';
import { Container, Card, CardContent, Typography, Button, CircularProgress } from '@mui/material';
import { getPersonalById } from '../services/PersonalService';

function Home() {

    const [personalData, setPersonalData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const personalId = 1;

    useEffect(() => {
        const fetchPersonalData = async () => {
            setLoading(true);
            setError(null);
            try {
                const data = await getPersonalById(personalId);
                setPersonalData(data);
                console.log("Data fetched:", data);
            } catch (err) {
                setError("Data Doesn't Exist");
                console.error("Error:", err);
            } finally {
                setLoading(false);
            }
        };

        fetchPersonalData();
    }, []);

    return (
        <Container 
            maxWidth="sm" 
            style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}
        >
            <Card 
                style={{ width: '100%', maxWidth: '400px', borderRadius: '16px' }} 
                elevation={3}
            >
                <CardContent>
                    {loading ? (
                        <CircularProgress style={{alignItems: 'center'}} />
                    ) : error ? (
                        <Typography variant="body2" color="error">
                            {error}
                        </Typography>
                    ) : personalData ? (
                        <>
                            <Typography variant="h5" component="div" style={{ textAlign: 'center',fontFamily: 'Poppins, sans-serif' }}>
                                Hi Im {personalData.firstName} {personalData.lastName}
                            </Typography>
                        </>
                    ) : (
                        <Typography variant='body2' color='text.secondary'>
                            Data Not Found
                        </Typography>
                    )}
                    <Button variant="contained" style={{ marginTop: '16px' }}>
                        Pelajari Lebih Lanjut
                    </Button>
                </CardContent>
            </Card>
        </Container>
    );
}

export default Home;