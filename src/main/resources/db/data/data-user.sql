
-- data - user
INSERT INTO public.tb_user
(username, full_name, date_of_birth, place_of_birth, email, pass, phone_number, address, profile_picture_id, role_id, status_id, created_at, created_by, updated_at, updated_by, is_active, versions) values
('super.user', 'Super User', '1990-12-12', 'Bekasi', 'super.user@newbie.com', '', '', '', NULL, 'SU', NULL, extract(EPOCH from (SELECT NOW())) * 1000, 'initiated-system', NULL, NULL, TRUE, 0),
('admin.question', 'Admin Question', '1990-12-12', 'Bekasi', 'admin.question@newbie.com', '', '', '', NULL, 'AD', NULL, extract(EPOCH from (SELECT NOW())) * 1000, 'initiated-system', NULL, NULL, TRUE, 0);