namespace MovieManager.forms
{
    partial class UpdateForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.boxUpdate = new System.Windows.Forms.GroupBox();
            this.checkBox4 = new System.Windows.Forms.CheckBox();
            this.checkBox3 = new System.Windows.Forms.CheckBox();
            this.checkBox1 = new System.Windows.Forms.CheckBox();
            this.checkBox2 = new System.Windows.Forms.CheckBox();
            this.boxTime = new System.Windows.Forms.GroupBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.cbMinute = new System.Windows.Forms.ComboBox();
            this.cbHours = new System.Windows.Forms.ComboBox();
            this.btnStart = new System.Windows.Forms.Button();
            this.btnRightNow = new System.Windows.Forms.Button();
            this.txtLog = new System.Windows.Forms.TextBox();
            this.timer = new System.Windows.Forms.Timer(this.components);
            this.boxUpdate.SuspendLayout();
            this.boxTime.SuspendLayout();
            this.SuspendLayout();
            // 
            // boxUpdate
            // 
            this.boxUpdate.Controls.Add(this.checkBox4);
            this.boxUpdate.Controls.Add(this.checkBox3);
            this.boxUpdate.Controls.Add(this.checkBox1);
            this.boxUpdate.Controls.Add(this.checkBox2);
            this.boxUpdate.Location = new System.Drawing.Point(523, 26);
            this.boxUpdate.Name = "boxUpdate";
            this.boxUpdate.Size = new System.Drawing.Size(412, 188);
            this.boxUpdate.TabIndex = 1;
            this.boxUpdate.TabStop = false;
            this.boxUpdate.Text = "업데이트 대상";
            // 
            // checkBox4
            // 
            this.checkBox4.AutoSize = true;
            this.checkBox4.Checked = true;
            this.checkBox4.CheckState = System.Windows.Forms.CheckState.Checked;
            this.checkBox4.Location = new System.Drawing.Point(30, 130);
            this.checkBox4.Name = "checkBox4";
            this.checkBox4.Size = new System.Drawing.Size(176, 24);
            this.checkBox4.TabIndex = 4;
            this.checkBox4.Text = "주간 박스오피스 순위";
            this.checkBox4.UseVisualStyleBackColor = true;
            // 
            // checkBox3
            // 
            this.checkBox3.AutoSize = true;
            this.checkBox3.Checked = true;
            this.checkBox3.CheckState = System.Windows.Forms.CheckState.Checked;
            this.checkBox3.Location = new System.Drawing.Point(30, 100);
            this.checkBox3.Name = "checkBox3";
            this.checkBox3.Size = new System.Drawing.Size(176, 24);
            this.checkBox3.TabIndex = 3;
            this.checkBox3.Text = "일간 박스오피스 순위";
            this.checkBox3.UseVisualStyleBackColor = true;
            // 
            // checkBox1
            // 
            this.checkBox1.AutoSize = true;
            this.checkBox1.Checked = true;
            this.checkBox1.CheckState = System.Windows.Forms.CheckState.Checked;
            this.checkBox1.Enabled = false;
            this.checkBox1.Location = new System.Drawing.Point(30, 40);
            this.checkBox1.Name = "checkBox1";
            this.checkBox1.Size = new System.Drawing.Size(96, 24);
            this.checkBox1.TabIndex = 2;
            this.checkBox1.Text = "최신 영화";
            this.checkBox1.UseVisualStyleBackColor = true;
            // 
            // checkBox2
            // 
            this.checkBox2.AutoSize = true;
            this.checkBox2.Checked = true;
            this.checkBox2.CheckState = System.Windows.Forms.CheckState.Checked;
            this.checkBox2.Location = new System.Drawing.Point(30, 70);
            this.checkBox2.Name = "checkBox2";
            this.checkBox2.Size = new System.Drawing.Size(111, 24);
            this.checkBox2.TabIndex = 1;
            this.checkBox2.Text = "영화 포스터";
            this.checkBox2.UseVisualStyleBackColor = true;
            // 
            // boxTime
            // 
            this.boxTime.Controls.Add(this.label2);
            this.boxTime.Controls.Add(this.label1);
            this.boxTime.Controls.Add(this.cbMinute);
            this.boxTime.Controls.Add(this.cbHours);
            this.boxTime.Location = new System.Drawing.Point(523, 220);
            this.boxTime.Name = "boxTime";
            this.boxTime.Size = new System.Drawing.Size(412, 129);
            this.boxTime.TabIndex = 2;
            this.boxTime.TabStop = false;
            this.boxTime.Text = "업데이트 예정 시각";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(213, 74);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(24, 20);
            this.label2.TabIndex = 4;
            this.label2.Text = "분";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(107, 74);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(24, 20);
            this.label1.TabIndex = 3;
            this.label1.Text = "시";
            // 
            // cbMinute
            // 
            this.cbMinute.DropDownHeight = 200;
            this.cbMinute.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbMinute.Font = new System.Drawing.Font("맑은 고딕", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.cbMinute.FormattingEnabled = true;
            this.cbMinute.IntegralHeight = false;
            this.cbMinute.Items.AddRange(new object[] {
            "0",
            "5",
            "10",
            "15",
            "20",
            "25",
            "30",
            "35",
            "40",
            "45",
            "50",
            "55"});
            this.cbMinute.Location = new System.Drawing.Point(136, 58);
            this.cbMinute.Name = "cbMinute";
            this.cbMinute.Size = new System.Drawing.Size(71, 36);
            this.cbMinute.TabIndex = 2;
            // 
            // cbHours
            // 
            this.cbHours.DropDownHeight = 200;
            this.cbHours.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbHours.Font = new System.Drawing.Font("맑은 고딕", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            this.cbHours.FormattingEnabled = true;
            this.cbHours.IntegralHeight = false;
            this.cbHours.Items.AddRange(new object[] {
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21",
            "22",
            "23"});
            this.cbHours.Location = new System.Drawing.Point(30, 58);
            this.cbHours.Name = "cbHours";
            this.cbHours.Size = new System.Drawing.Size(71, 36);
            this.cbHours.TabIndex = 1;
            // 
            // btnStart
            // 
            this.btnStart.Location = new System.Drawing.Point(840, 390);
            this.btnStart.Name = "btnStart";
            this.btnStart.Size = new System.Drawing.Size(95, 57);
            this.btnStart.TabIndex = 3;
            this.btnStart.Text = "시작";
            this.btnStart.UseVisualStyleBackColor = true;
            // 
            // btnRightNow
            // 
            this.btnRightNow.Location = new System.Drawing.Point(736, 390);
            this.btnRightNow.Name = "btnRightNow";
            this.btnRightNow.Size = new System.Drawing.Size(95, 57);
            this.btnRightNow.TabIndex = 4;
            this.btnRightNow.Text = "지금 당장";
            this.btnRightNow.UseVisualStyleBackColor = true;
            // 
            // txtLog
            // 
            this.txtLog.Location = new System.Drawing.Point(26, 26);
            this.txtLog.Multiline = true;
            this.txtLog.Name = "txtLog";
            this.txtLog.Size = new System.Drawing.Size(491, 421);
            this.txtLog.TabIndex = 5;
            // 
            // timer
            // 
            this.timer.Interval = 1000;
            // 
            // UpdateForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(947, 459);
            this.Controls.Add(this.txtLog);
            this.Controls.Add(this.btnRightNow);
            this.Controls.Add(this.btnStart);
            this.Controls.Add(this.boxTime);
            this.Controls.Add(this.boxUpdate);
            this.Name = "UpdateForm";
            this.Text = "UpdateForm";
            this.Load += new System.EventHandler(this.UpdateForm_Load);
            this.boxUpdate.ResumeLayout(false);
            this.boxUpdate.PerformLayout();
            this.boxTime.ResumeLayout(false);
            this.boxTime.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private GroupBox boxUpdate;
        private CheckBox checkBox4;
        private CheckBox checkBox3;
        private CheckBox checkBox1;
        private CheckBox checkBox2;
        private GroupBox boxTime;
        private Label label2;
        private Label label1;
        private ComboBox cbMinute;
        private ComboBox cbHours;
        private Button btnStart;
        private Button btnRightNow;
        private TextBox txtLog;
        private System.Windows.Forms.Timer timer;
    }
}