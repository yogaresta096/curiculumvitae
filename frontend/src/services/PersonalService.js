import axios from "axios";

const API_URL = process.env.REACT_APP_API_URL;

export const getPersonalById = async (id) => {
    console.log("Fetching personal data for ID:", id);
    try {
        const response = await fetch(`${API_URL}/cv/personal/${id}`);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Error fetching personal data by ID:", error);
        throw error;
    }
};