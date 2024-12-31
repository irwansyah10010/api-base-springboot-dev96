-- ROLE
INSERT INTO public.tb_role
(role_code, role_name, description, created_at, created_by, updated_at, updated_by, versions, is_active) VALUES
('SU', 'Super', 'description Super' ,extract(EPOCH from (SELECT NOW())) * 1000, 'initiated-system', NULL, NULL, 0, true),
('AD', 'Admin', 'description Admin',extract(EPOCH from (SELECT NOW())) * 1000, 'initiated-system', NULL, NULL, 0, true),
('RV', 'Reviewer', 'description Reviewer',extract(EPOCH from (SELECT NOW())) * 1000, 'initiated-system', NULL, NULL, 0, true),
('CD', 'Candidate', 'description Candidate',extract(EPOCH from (SELECT NOW())) * 1000, 'initiated-system', NULL, NULL, 0, true);

--