## Copyright (c) 2023 Contributors to the Eclipse Foundation
##
## See the NOTICE file(s) distributed with this work for additional
## information regarding copyright ownership.
##
## This program and the accompanying materials are made available under the
## terms of the Apache License, Version 2.0 which is available at
## https://www.apache.org/licenses/LICENSE-2.0. *
## Unless required by applicable law or agreed to in writing, software
## distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
## WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
## License for the specific language governing permissions and limitations
## under the License.
##
## * SPDX-License-Identifier: Apache-2.0

create table registry_lookup_metrics
(
	id                                    int8 generated by default as identity,
	status                                int4,
	start_date                            timestamp,
	end_date                              timestamp,
	success_shell_descriptors_fetch_count int8,
	failed_shell_descriptors_fetch_count  int8,
	primary key (id)
);
