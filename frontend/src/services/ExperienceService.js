import { getPersonalById } from '../services/PersonalService'; 
const API_URL = process.env.REACT_APP_API_URL;

export const getExperienceByPersonalId = async (id) => {
    try {
        const personalData = await getPersonalById(id);
        console.log("Fetching from URL: ", `${API_URL}/cv/experiences/${personalData.id}`);

        if (!personalData || !personalData.id) {
            throw new Error("Invalid Personal Data Received");
        }

        const response = await fetch(`${API_URL}/cv/experiences/${personalData.id}`);

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(`HTTP Error! status: ${response.status}, message: ${errorText}`);
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching experience data!', error);
        throw error;
    }
};